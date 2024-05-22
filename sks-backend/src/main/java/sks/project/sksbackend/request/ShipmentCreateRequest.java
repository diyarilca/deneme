package sks.project.sksbackend.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShipmentCreateRequest {

	
		private Long companyId;
	    private Long productId;
	    private Long shipId;
	    private List<Long> vehicleIds;
	    
	    private String departurePoint;
	    private String destinationPoint;
	    private String customerPhone;
	    private String price;
	    private String comment;
}
