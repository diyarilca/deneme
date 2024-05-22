package sks.project.sksbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentCreateRequest {
    private String departurePoint;
    private String destinationPoint;
    private String customerPhone;
    private String price;
    private String comment;
    private CompanyRequest company;
    private ProductRequest product;
    private ShipRequest ship;
    private List<VehicleRequest> vehicles;

    // Getters and Setters

    public static class CompanyRequest {
        private Long id;
        private String companyName;
        private String phoneNumber;
        private String email;
        private String taxOffice;
        private String taxNumber;
        private String address;
        // Getters and Setters
    }

    public static class ProductRequest {
        private Long id;
        private String productName;
        // Getters and Setters
    }

    public static class ShipRequest {
        private Long id;
        private String shipName;
        private String exporter;
        private String departurePort;
        private String destinationPort;
        // Getters and Setters
    }

    public static class VehicleRequest {
        private Long id;
        private String vehicleType;
        private String driverName;
        private String driverPhone;
        private String towPlate;
        private String trailerPlate;
        // Getters and Setters
    }

    // Getters and Setters for ShipmentCreateRequest fields
}
