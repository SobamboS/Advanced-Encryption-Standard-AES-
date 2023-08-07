package com.example.encryptionanddecryption.AES;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Service
@Slf4j
public class License{

        @Id
        @Column(nullable = false, name = "Id")
        @SequenceGenerator(name = "seq",initialValue = 100,allocationSize = 1)
        @GeneratedValue(generator = "seq",strategy= GenerationType.SEQUENCE)
        private int id;

        @Column(name = "license", length = 8)
        private String license;
}
