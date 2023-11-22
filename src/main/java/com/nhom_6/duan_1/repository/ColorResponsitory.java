package com.nhom_6.duan_1.repository;

import com.nhom_6.duan_1.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColorResponsitory extends JpaRepository<Color, Long> {
    @Query("SELECT c FROM Color c WHERE c.nameColor = :nameColor")
    public Color getByName(@Param("nameColor") String nameColor);
}
