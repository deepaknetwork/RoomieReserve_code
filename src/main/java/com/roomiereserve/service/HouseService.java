package com.roomiereserve.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.roomiereserve.dto.AddHouse;
import com.roomiereserve.dto.DecodedHouse;
import com.roomiereserve.dto.DecodedRoom;
import com.roomiereserve.dto.HouseDetails;
import com.roomiereserve.dto.ReserveDetails;
import com.roomiereserve.dto.UpdateRoom;
import com.roomiereserve.dto.viewRoom;
import com.roomiereserve.exception.NotExist;
import com.roomiereserve.exception.Restricted;
import com.roomiereserve.model.Amenitie;
import com.roomiereserve.model.Booking;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.House;
import com.roomiereserve.model.Image;
import com.roomiereserve.model.Owner;
import com.roomiereserve.model.Reserve;
import com.roomiereserve.model.Room;
import com.roomiereserve.repository.CustomerRepository;
import com.roomiereserve.repository.HouseRepository;
import com.roomiereserve.repository.ImageRepository;
import com.roomiereserve.repository.OwnerRepository;
import com.roomiereserve.repository.ReserveRepository;

@Service
public class HouseService {

	HouseRepository houserrepo;
	CustomerRepository customerrepo;
	OwnerRepository ownerrepo;
	ReserveRepository reserverepo;
	ImageRepository imgrepo;
	OwnerService ownerser;
	CustomerService customerser;
	Orgservice orgser;

	public HouseService(HouseRepository houserrepo, CustomerRepository customerrepo, OwnerRepository ownerrepo,
			ReserveRepository reserverepo, ImageRepository imgrepo, OwnerService ownerser, CustomerService customerser,
			Orgservice orgser) {
		this.houserrepo = houserrepo;
		this.customerrepo = customerrepo;
		this.ownerrepo = ownerrepo;
		this.reserverepo = reserverepo;
		this.imgrepo = imgrepo;
		this.ownerser = ownerser;
		this.customerser = customerser;
		this.orgser = orgser;
	}

	public House findHouseByAddress(String address) {
		try {
			return houserrepo.findByAddress(address);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public List<viewRoom> allRooms() {
		List<viewRoom> rooms = new ArrayList<>();
		List<House> houses = houserrepo.findAll();
		for (House house : houses) {
			for (Room room : house.getRooms()) {
				if (room.amenities.size() > 0 || room.images.size() > 0) {
					rooms.add(new viewRoom(room.houseNo, room.roomNo, house.getAddress(), room.area, room.beds,
							room.rent, room.bookings, room.amenities, room.images));
				}
			}
		}
		return rooms;
	}

	public void addHouse(Long phone, AddHouse house) {
		Owner owner = ownerser.findOwnerByPhone(phone);
		if (owner == null) {
			throw new NotExist("No owner found");
		}
		House temphouse = findHouseByAddress(house.address);
		if (temphouse != null) {
			throw new Restricted("house aldready exsist");
		}
		try {
			houserrepo.save(new House(orgser.getHouseId(), owner.getPhone(), house.address, new ArrayList<>()));
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public void addRoom(Long phone, Room room) {
		House temphouse = houserrepo.findByHouseId(room.houseNo);
		if (temphouse == null) {
			throw new RuntimeException("house not exixt");
		}
		temphouse.getRooms().add(room);
		houserrepo.save(temphouse);

	}

	public Room getRoom(Long houseNo, Long roomNo) {
		House temphouse = houserrepo.findByHouseId(houseNo);
		if (temphouse == null) {
			throw new RuntimeException("house not exixt");
		}
		List<Room> rooms = temphouse.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == roomNo) {
				index = inc;
				break;
			}
			inc++;
		}
		if (index == -1) {
			throw new NotExist("room not found");
		}
		return rooms.get(index);

	}

	public void patchRoom(Long houseId, Long roomId, UpdateRoom roomDetails) {
		House house = oneHouse(houseId);
		if (house == null) {
			throw new NotExist("No house found");
		}
		List<Room> rooms = house.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == roomId) {
				index = inc;
				break;
			}
			inc++;
		}
		if (index == -1) {
			throw new NotExist("room not found");
		}
		Room temproom = rooms.get(index);
		if (roomDetails.beds != temproom.beds && roomDetails.beds > 0) {
			temproom.beds = roomDetails.beds;
		}
		if (roomDetails.rent != temproom.rent && roomDetails.rent > 400) {
			temproom.rent = roomDetails.rent;
		}
		if (roomDetails.amenities != null) {
			temproom.amenities = roomDetails.amenities;
		}
		rooms.set(index, temproom);
		house.setRooms(rooms);
		houserrepo.save(house);
	}

	public List<House> allHouse() {
		try {
			return houserrepo.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public List<House> OwnerHouse(Long phone) {
		try {
			return houserrepo.findAllByOwnerId(phone);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public House oneHouse(Long houseId) {
		House house;
		try {
			house = houserrepo.findByHouseId(houseId);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
		if (house == null) {
			throw new NotExist("House not found");
		}
		return house;

	}

	public void reserveHouse(Long phone, ReserveDetails reserve) {
		Customer customer = customerrepo.findByPhone(phone);
		Owner customer1 = ownerrepo.findByPhone(phone);
		House house = oneHouse(reserve.id());
		Owner owner = ownerser.findOwnerByPhone(house.getOwnerId());

		List<Room> rooms = house.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == reserve.roomNo()) {
				index = inc;
				break;
			}
			inc++;
		}

		if (index == -1) {
			throw new NotExist("room not found");
		}

		Room temproom = rooms.get(index);
		Long reserveId = orgser.getReserveId();
		Long rent = temproom.rent * reserve.day();
		if (customer != null) {
			if (customer.getBalance() < rent) {
				throw new RuntimeException("insufficient balance, please topup");
			}
		} else {
			if (customer1.getBalance() < rent) {
				throw new RuntimeException("insufficient balance, please topup");
			}
		}

		reserverepo.save(new Reserve(reserveId, reserve.id(), reserve.roomNo(), house.getAddress(), house.getOwnerId(),
				phone, LocalDate.now(), reserve.date(), reserve.member(), reserve.day(), rent));
		if (customer != null) {
			temproom.bookings
					.add(new Booking(reserveId, customer.getPhone(), reserve.date(), reserve.member(), reserve.day()));
		} else {
			temproom.bookings
					.add(new Booking(reserveId, customer1.getPhone(), reserve.date(), reserve.member(), reserve.day()));
		}

		rooms.set(index, temproom);
		house.setRooms(rooms);
		houserrepo.save(house);
		if (customer != null) {
			customer.setBalance(customer.getBalance() - rent);
			customerrepo.save(customer);
		} else {
			customer1.setBalance(customer1.getBalance() - rent);
			ownerrepo.save(customer1);
		}
		owner.setBalance(owner.getBalance() + rent);
		ownerrepo.save(owner);

	}

	public void addRoomWithImage(Long OwnerId, Long houseId, Long roomId, MultipartFile image) throws IOException {
		Owner owner = ownerser.findOwnerByPhone(OwnerId);
		House house = oneHouse(houseId);

		List<Room> rooms = house.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == roomId) {
				index = inc;
				break;
			}
			inc++;
		}
		if (index == -1) {
			throw new NotExist("room not found");
		}
		Room temproom = rooms.get(index);
		long imgId = orgser.getImageId();
		imgrepo.save(new Image(Long.toString(imgId), Base64.getEncoder().encodeToString(image.getBytes())));
		temproom.images.addFirst(Long.toString(imgId));
		rooms.set(index, temproom);
		house.setRooms(rooms);
		houserrepo.save(house);
	}

	public Room oneRoom(Long houseId, Long roomId) {
		House house = oneHouse(houseId);
		if (house == null) {
			throw new NotExist("No house found");
		}
		List<Room> rooms = house.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == roomId) {
				index = inc;
				break;
			}
			inc++;
		}
		if (index == -1) {
			throw new NotExist("room not found");
		}
		return rooms.get(index);
	}

	public byte[] oneImage(String id) {
		Image img = imgrepo.findByImgid(id);
		return Base64.getDecoder().decode(img.img);
	}

	public void deleteImage(Long houseId, Long roomId, String id) {
		try {
			imgrepo.deleteByImgid(id);
			House house = oneHouse(houseId);
			if (house == null) {
				throw new NotExist("No house found");
			}
			List<Room> rooms = house.getRooms();
			int index = -1, inc = 0;
			for (Room room : rooms) {
				if (room.roomNo == roomId) {
					index = inc;
					break;
				}
				inc++;
			}
			if (index == -1) {
				throw new NotExist("room not found");
			}
			Room temproom = rooms.get(index);
			temproom.images.remove(id);
			rooms.set(index, temproom);
			house.setRooms(rooms);
			houserrepo.save(house);

		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public void deleteRoom(Long houseId, Long roomId) {
		House house = oneHouse(houseId);
		if (house == null) {
			throw new NotExist("No house found");
		}
		List<Room> rooms = house.getRooms();
		int index = -1, inc = 0;
		for (Room room : rooms) {
			if (room.roomNo == roomId) {
				index = inc;
				break;
			}
			inc++;
		}
		if (index == -1) {
			throw new NotExist("room not found");
		}
		Room temproom = rooms.remove(index);
		rooms.set(index, temproom);
		house.setRooms(rooms);
		houserrepo.save(house);
	}

	public void deleteHouse(Long houseId) {
		House house = oneHouse(houseId);
		if (house.getRooms().size() != 0) {
			throw new RuntimeException("ensure to remove all of rooms");
		}
		try {
			houserrepo.deleteByHouseId(houseId);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public List<Reserve> getbookingsRoom(Long houseId, Long roomId) {
		List<Reserve> reserves = reserverepo.findAllByHouseId(houseId);
		List<Reserve> roomReserve = new ArrayList<>();
		for (Reserve reserve : reserves) {
			if (reserve.getRoomId() == roomId) {
				roomReserve.add(reserve);
			}
		}
		return roomReserve;
	}

	public Reserve reserveById(Long id) {
		return reserverepo.findByReserveId(id);
	}

	public List<Reserve> reserveByCustomerId(Long phone) {
		return reserverepo.findAllByCustomerId(phone);
	}

	public List<Reserve> reserveByOwnerId(Long phone) {
		return reserverepo.findAllByOwnerId(phone);
	}
}
