package com.roomiereserve.dto;

import java.time.LocalDate;

public record ReserveDetails(Long id,Long roomNo,Long member,LocalDate date,Long day) {
	
}
