package ru.skypro.my.sockWarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.my.sockWarehouse.model.Sock;

@Repository
public interface SocksRepository extends JpaRepository<Sock, String> {
}
