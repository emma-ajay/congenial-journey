package com.ajayCodes.calebFyp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    @Id
    @SequenceGenerator(name = "data_sequence",
            sequenceName = "data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "data_sequence"
    )

    private Long dataId;
    private String heartrate;
    private String oxygenLevel;
    private String longLat;
    private String dateTime;

}
