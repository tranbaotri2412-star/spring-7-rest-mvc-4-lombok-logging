package guru.springframework.spring7restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
public class Customer {
    private String name;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
