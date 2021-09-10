package pl.kodokan.fcp.server.entrance.dto;

import java.util.List;

public interface MapperToDto <D,E> {
    D toDto(E entity);
    List<D> toDto(List<E> entities);
}
