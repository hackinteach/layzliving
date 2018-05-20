package io.muic.cs.ooc.lazyliving.backend.entity;



import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="energy_record")
public class EnergyRecord {
    @Id
    @GeneratedValue
    @Column(name="energy_record_id")
    private long energyRecordId;

    LocalDate date;

    @OneToOne
    Device device;

    double energyConsumed;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
}
