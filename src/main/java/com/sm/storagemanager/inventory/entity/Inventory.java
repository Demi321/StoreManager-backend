package com.sm.storagemanager.inventory.entity;

import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "stock_on_hand", nullable = false, precision = 14, scale = 3)
    private BigDecimal stockOnHand;

    @Column(name = "stock_reserved", nullable = false, precision = 14, scale = 3)
    private BigDecimal stockReserved;

    @Column(name = "stock_available", precision = 14, scale = 3, insertable = false, updatable = false)
    private BigDecimal stockAvailable;

    @Column(name = "average_cost", nullable = false, precision = 14, scale = 2)
    private BigDecimal averageCost;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
