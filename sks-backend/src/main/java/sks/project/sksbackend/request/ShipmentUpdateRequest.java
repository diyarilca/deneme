package sks.project.sksbackend.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentUpdateRequest {
    
    private String departurePoint;
    private String destinationPoint;
    private String customerPhone;
    private String price;
    private String comment;
    private Long companyId;
    private Long productId;
    private Long shipId;
    private List<Long> vehicleIds;

   
}
