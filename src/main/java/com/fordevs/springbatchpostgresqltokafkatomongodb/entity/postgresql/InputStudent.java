package com.fordevs.springbatchpostgresqltokafkatomongodb.entity.postgresql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "student")
public class InputStudent {

	@Id
	private String id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	@Column(name = "dept_id")
	private Long deptId;

	@Column(name = "is_active")
	private String isActive;

}
