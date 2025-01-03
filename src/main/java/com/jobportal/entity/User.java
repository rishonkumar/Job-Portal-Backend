package com.jobportal.entity;

import com.jobportal.dto.AccountType;
import com.jobportal.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true) //email will be unique there will be no duplicate
    private String email;
    private String password;
    private AccountType accountType;

    private Long profileId;

    public UserDto toUserDto() {
        return new UserDto(this.id,this.name,this.email,this.password,this.accountType,this.profileId);
    }
}
