package com.project.hotel_api_p2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_quartos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String tipo;
    private BigDecimal precoDiaria;
    private Boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
