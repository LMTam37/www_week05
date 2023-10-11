package vn.edu.iuh.fit.week_05.services;

import jakarta.persistence.EntityNotFoundException;
import vn.edu.iuh.fit.week_05.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address saveAddress(Address address);
    Optional<Address> getAddressById(Long id);
    List<Address> getAllAddresses();
    Address updateAddress(Long id, Address updatedAddress);
    void deleteAddress(Long id);
}

