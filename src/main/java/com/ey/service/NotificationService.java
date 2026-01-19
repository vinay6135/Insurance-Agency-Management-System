package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.response.NotificationResponseDTO;
import com.ey.entity.Customer;
import com.ey.entity.Notification;
import com.ey.exception.BadRequestException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.CustomerRepository;
import com.ey.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private CustomerRepository customerrepo;

    @Autowired
    private NotificationRepository repository;

    public void notify(Customer customer, String message) {

        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setMessage(message);

        repository.save(notification);
    }
    
    public List<NotificationResponseDTO> toResponse(long id,String email)
    {
    	if(customerrepo.findById(id).get().getUser().getEmail().equals(email))
    	{
    		List<Notification> list=repository.findByCustomerId(id);
    		if(!list.isEmpty())
    		{
    			List<NotificationResponseDTO> dtores=new ArrayList<>();
    			
    			for(Notification notification:list)
    			{
    				NotificationResponseDTO res=new NotificationResponseDTO();
    				res.setId(notification.getId());
    				res.setMessage(notification.getMessage());
    				res.setCustomerId(notification.getCustomer().getId());
    				dtores.add(res);
    				
    			}
    			return dtores;
    		}
    		throw new ResourceNotFoundException("No Notifiations Received");
    	}
    	throw new BadRequestException("Incorrect Customer Id");

    	
    	
    }
}

