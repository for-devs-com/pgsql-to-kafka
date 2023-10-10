package com.fordevs.springbatchpostgresqltokafka.entity.postgresql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "students")
public class InputStudent {


	private String id;


	private String firstName;

	private String lastName;

	private String email;

	private Long deptId;

	private String isActive;

}
