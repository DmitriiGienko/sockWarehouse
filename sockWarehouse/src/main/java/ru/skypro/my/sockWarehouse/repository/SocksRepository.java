package ru.skypro.my.sockWarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.my.sockWarehouse.model.SockId;
import ru.skypro.my.sockWarehouse.model.Sock;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Sock, SockId> {

     Optional<Sock> findById(SockId sockId);
}
