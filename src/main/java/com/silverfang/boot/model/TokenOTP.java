package com.silverfang.boot.model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class TokenOTP {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

//    @OneToOne(targetEntity = UserTable.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
//    private UserTable user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}

