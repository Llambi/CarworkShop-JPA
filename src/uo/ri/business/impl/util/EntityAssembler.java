package uo.ri.business.impl.util;

import uo.ri.business.dto.*;
import uo.ri.model.*;
import uo.ri.model.types.Address;

public class EntityAssembler {

    public static Mecanico toEntity(MechanicDto dto) {
        return new Mecanico(dto.dni, dto.name, dto.surname);
    }

    public static Cliente toEntity(ClientDto dto) {
        Cliente c = new Cliente(dto.dni, dto.name, dto.surname);
        Address addr = new Address(
                dto.addressStreet, dto.addressCity, dto.addressZipcode);
        c.setAddress(addr);
        c.setPhone(dto.phone);
        c.setEmail(dto.email);
        return c;
    }

    public static ContractCategory toEntity(ContractCategoryDto dto) {
        return new ContractCategory(dto.name, dto.trieniumSalary,
                dto.productivityPlus);
    }

    public static ContractType toEntity(ContractTypeDto dto) {
        return new ContractType(dto.name, dto.compensationDays);
    }

    public static Contract toEntity(ContractDto dto, Mecanico m) {
        return new Contract(m, dto.startDate, dto.endDate, dto.yearBaseSalary);
    }
}
