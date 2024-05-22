package sks.project.sksbackend.serviceBusiness.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sks.project.sksbackend.entities.Company;
import sks.project.sksbackend.entities.Product;
import sks.project.sksbackend.entities.Ship;
import sks.project.sksbackend.entities.Shipment;
import sks.project.sksbackend.entities.Vehicle;
import sks.project.sksbackend.exception.ResourceNotFoundException;
import sks.project.sksbackend.repositoryDataAccess.ShipmentRepository;
import sks.project.sksbackend.request.ShipmentCreateRequest;
import sks.project.sksbackend.request.ShipmentUpdateRequest;
import sks.project.sksbackend.response.ShipmentResponse;
import sks.project.sksbackend.serviceBusiness.CompanyService;
import sks.project.sksbackend.serviceBusiness.ProductService;
import sks.project.sksbackend.serviceBusiness.ShipService;
import sks.project.sksbackend.serviceBusiness.ShipmentService;
import sks.project.sksbackend.serviceBusiness.VehicleService;


@AllArgsConstructor
@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	
	
    private ShipmentRepository shipmentRepository;
    private CompanyService companyService;
    private ProductService productService;
    private ShipService shipService;
    private VehicleService vehicleService;
    

  
	
    @Override
	public Shipment createShipment(ShipmentCreateRequest request) {
	    Company company = companyService.getCompanyById(request.getCompanyId());
	    Product product = productService.getProductById(request.getProductId());
	    Ship ship = shipService.getShipById(request.getShipId());
	    List<Vehicle> vehicles = vehicleService.getVehiclesByIds(request.getVehicleIds());

	    if (company != null && product != null && ship != null && vehicles != null && !vehicles.isEmpty()) {
	        Shipment shipmentToSave = new Shipment();
	        shipmentToSave.setDeparturePoint(request.getDeparturePoint());
	        shipmentToSave.setDestinationPoint(request.getDestinationPoint());
	        shipmentToSave.setCustomerPhone(request.getCustomerPhone());
	        shipmentToSave.setPrice(request.getPrice());
	        shipmentToSave.setComment(request.getComment());
	        shipmentToSave.setCompany(company);
	        shipmentToSave.setProduct(product);
	        shipmentToSave.setShip(ship);
	        shipmentToSave.setVehicles(vehicles);
	        return shipmentRepository.save(shipmentToSave);
	    } else {
	        throw new IllegalArgumentException("Company, Product, Ship, ve Vehicles boş olamaz ve Vehicles listesi boş olamaz");
	    }
	}
    
    @Override
    public void deleteShipment(Long shipmentId) {
    	Shipment shipment = shipmentRepository.findById(shipmentId)
    			.orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil" + shipmentId));
  
    	shipmentRepository.delete(shipment);
    }
    
    @Override
    public Shipment getShipmentById(Long shipmentId) {
    	return shipmentRepository.findById(shipmentId)
    			 .orElseThrow(() -> new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil " + shipmentId));
    }
    
   @Override
   public List<ShipmentResponse> getAllShipments() {
            List<Shipment> shipments = shipmentRepository.findAll();
            return shipments.stream().map(shipment -> new ShipmentResponse(shipment)).collect(Collectors.toList());
    }
   
   @Override
   public ShipmentResponse updateShipment(Long shipmentId, ShipmentUpdateRequest request) {
       Optional<Shipment> shipmentOpt = shipmentRepository.findById(shipmentId);
       if (shipmentOpt.isPresent()) {
           Shipment shipmentToUpdate = shipmentOpt.get();
           
           // Güncelleme işlemleri
           shipmentToUpdate.setDeparturePoint(request.getDeparturePoint());
           shipmentToUpdate.setDestinationPoint(request.getDestinationPoint());
           shipmentToUpdate.setCustomerPhone(request.getCustomerPhone());
           shipmentToUpdate.setPrice(request.getPrice());
           shipmentToUpdate.setComment(request.getComment());
           
           // İlişkili nesneler için güncelleme işlemleri
           if (request.getCompanyId() != null) {
               Company company = new Company();
               company.setId(request.getCompanyId());
               shipmentToUpdate.setCompany(company);
           }
           if (request.getProductId() != null) {
               Product product = new Product();
               product.setId(request.getProductId());
               shipmentToUpdate.setProduct(product);
           }
           if (request.getShipId() != null) {
               Ship ship = new Ship();
               ship.setId(request.getShipId());
               shipmentToUpdate.setShip(ship);
           }
           if (request.getVehicleIds() != null && !request.getVehicleIds().isEmpty()) {
               List<Vehicle> vehicles = request.getVehicleIds().stream()
                                               .map(id -> {
                                                   Vehicle vehicle = new Vehicle();
                                                   vehicle.setId(id);
                                                   return vehicle;
                                               })
                                               .collect(Collectors.toList());
               shipmentToUpdate.setVehicles(vehicles);
           }
           
           // Güncellenmiş nesneyi kaydet
           Shipment updatedShipment = shipmentRepository.save(shipmentToUpdate);
           return new ShipmentResponse(updatedShipment);
       } else {
           // Eğer sevkiyat bulunamazsa null döndür
    	   throw new ResourceNotFoundException("Verilen kimliğe sahip sevkiyat mevcut değil." + shipmentId);
       }
   }
   
   
   
   
}


	 
	    




