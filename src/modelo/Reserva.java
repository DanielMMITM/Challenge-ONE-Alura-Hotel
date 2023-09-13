package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {
    private Integer id;

    private LocalDateTime fecha_entrada;

    private LocalDateTime fecha_salida;

    private Integer valor;

    private String forma_de_pago;

    public Reserva() {

    }

    public Reserva(LocalDateTime fecha_entrada, LocalDateTime fecha_salida, Integer valor, String forma_de_pago) {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.valor = valor;
        this.forma_de_pago = forma_de_pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getForma_de_pago() {
        return forma_de_pago;
    }

    public void setForma_de_pago(String forma_de_pago) {
        this.forma_de_pago = forma_de_pago;
    }

}
