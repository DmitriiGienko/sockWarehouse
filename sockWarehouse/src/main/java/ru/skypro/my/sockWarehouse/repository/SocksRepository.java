package ru.skypro.my.sockWarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.my.sockWarehouse.model.SockId;
import ru.skypro.my.sockWarehouse.model.Sock;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Sock, SockId> {

    Optional<Sock> findById(SockId sockId);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color AND s.cottonPart > :cottonPart")
    Optional<Integer> sumOfSocksMoreThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color AND s.cottonPart < :cottonPart")
    Optional<Integer> sumOfSocksLessThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query(value = "SELECT quantity FROM sock WHERE color = :color AND cotton_part = :cottonPart",
            nativeQuery = true)
    Optional<Integer> sumOfSocksEqual(@Param("color") String color, @Param("cottonPart") Integer cottonPart);
}

