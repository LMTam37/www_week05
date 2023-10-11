package vn.edu.iuh.fit.week_05.services.impl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_05.models.Address;
import vn.edu.iuh.fit.week_05.repositories.AddressRepository;
import vn.edu.iuh.fit.week_05.services.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();

            if (updatedAddress.getStreet() != null && !updatedAddress.getStreet().isEmpty()) {
                existingAddress.setStreet(updatedAddress.getStreet());
            }

            if (updatedAddress.getCity() != null && !updatedAddress.getCity().isEmpty()) {
                existingAddress.setCity(updatedAddress.getCity());
            }

            if (updatedAddress.getCountry() != null) {
                existingAddress.setCountry(updatedAddress.getCountry());
            }

            if (updatedAddress.getNumber() != null && !updatedAddress.getNumber().isEmpty()) {
                existingAddress.setNumber(updatedAddress.getNumber());
            }

            if (updatedAddress.getZipcode() != null && !updatedAddress.getZipcode().isEmpty()) {
                existingAddress.setZipcode(updatedAddress.getZipcode());
            }

            return addressRepository.save(existingAddress);
        } else {
            throw new EntityNotFoundException("Address not found with id: " + id);
        }
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
