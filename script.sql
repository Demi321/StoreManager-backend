BEGIN;

-- =========================================================
-- ENUM TYPES
-- =========================================================

CREATE TYPE entity_status AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');

CREATE TYPE user_status AS ENUM ('ACTIVE', 'INACTIVE', 'LOCKED');

CREATE TYPE warehouse_type AS ENUM ('WAREHOUSE', 'STORE', 'COUNTER', 'BRANCH');

CREATE TYPE inventory_movement_type AS ENUM (
    'PURCHASE_IN',
    'SALE_OUT',
    'ADJUSTMENT_IN',
    'ADJUSTMENT_OUT',
    'CUSTOMER_RETURN_IN',
    'SUPPLIER_RETURN_OUT',
    'WASTE_OUT',
    'TRANSFER_IN',
    'TRANSFER_OUT'
);

CREATE TYPE sale_status AS ENUM ('PENDING', 'PAID', 'CANCELLED');

CREATE TYPE sale_type AS ENUM ('COUNTER', 'CREDIT', 'LAYAWAY');

CREATE TYPE purchase_status AS ENUM ('DRAFT', 'RECEIVED', 'CANCELLED');

CREATE TYPE customer_type AS ENUM ('GENERAL', 'INDIVIDUAL', 'BUSINESS');

CREATE TYPE payment_method_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE role_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE permission_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE product_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE category_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE supplier_status AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TYPE payment_method_code AS ENUM (
    'CASH',
    'CARD',
    'TRANSFER',
    'MOBILE_PAYMENT',
    'MIXED',
    'OTHER'
);

-- =========================================================
-- CORE / TENANT TABLES
-- =========================================================

CREATE TABLE sector (
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(150) NOT NULL,
    description         TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_sector_name UNIQUE (name)
);

CREATE INDEX idx_sector_name ON sector (name);

CREATE TABLE business_entity (
    id                  BIGSERIAL PRIMARY KEY,
    sector_id           BIGINT NOT NULL,
    name                VARCHAR(150) NOT NULL,
    legal_name          VARCHAR(200),
    tax_id              VARCHAR(30),
    phone               VARCHAR(30),
    email               VARCHAR(150),
    status              entity_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_business_entity_sector
        FOREIGN KEY (sector_id) REFERENCES sector (id)
        ON DELETE RESTRICT,

    CONSTRAINT uq_business_entity_tax_id UNIQUE (tax_id)
);

CREATE INDEX idx_business_entity_sector ON business_entity (sector_id);
CREATE INDEX idx_business_entity_status ON business_entity (status);
CREATE INDEX idx_business_entity_name ON business_entity (name);

-- =========================================================
-- LOCATION CATALOGS
-- =========================================================

CREATE TABLE country (
    id                  BIGSERIAL PRIMARY KEY,
    code                VARCHAR(10) NOT NULL,
    name                VARCHAR(100) NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_country_code UNIQUE (code),
    CONSTRAINT uq_country_name UNIQUE (name)
);

CREATE TABLE state (
    id                  BIGSERIAL PRIMARY KEY,
    country_id          BIGINT NOT NULL,
    code                VARCHAR(10) NOT NULL,
    name                VARCHAR(100) NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_state_country
        FOREIGN KEY (country_id) REFERENCES country (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_state_country_code UNIQUE (country_id, code),
    CONSTRAINT uq_state_country_name UNIQUE (country_id, name),

    CONSTRAINT uq_state_id_country UNIQUE (id, country_id)
);

CREATE INDEX idx_state_country ON state (country_id);
CREATE INDEX idx_state_country_name ON state (country_id, name);

CREATE TABLE branch (
    id                  BIGSERIAL PRIMARY KEY,
    business_entity_id  BIGINT NOT NULL,
    name                VARCHAR(150) NOT NULL,
    code                VARCHAR(50),
    phone               VARCHAR(30),
    email               VARCHAR(150),
    is_active           BOOLEAN NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_branch_business_entity
        FOREIGN KEY (business_entity_id) REFERENCES business_entity (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_branch_entity_name UNIQUE (business_entity_id, name),
    CONSTRAINT uq_branch_entity_code UNIQUE (business_entity_id, code)
);

CREATE INDEX idx_branch_entity_active
    ON branch (business_entity_id, is_active);

CREATE INDEX idx_branch_entity_name
    ON branch (business_entity_id, name);

CREATE TABLE branch_address (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    country_id          BIGINT NOT NULL,
    state_id            BIGINT NOT NULL,
    address_line_1      VARCHAR(200) NOT NULL,
    address_line_2      VARCHAR(200),
    city                VARCHAR(100),
    postal_code         VARCHAR(20),
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_branch_address_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_branch_address_country
        FOREIGN KEY (country_id) REFERENCES country (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_branch_address_state_country
        FOREIGN KEY (state_id, country_id) REFERENCES state (id, country_id)
        ON DELETE RESTRICT,

    CONSTRAINT uq_branch_address_branch UNIQUE (branch_id)
);

CREATE INDEX idx_branch_address_country
    ON branch_address (country_id);

CREATE INDEX idx_branch_address_state
    ON branch_address (state_id);

CREATE INDEX idx_branch_address_city
    ON branch_address (city);

-- =========================================================
-- SECURITY TABLES
-- =========================================================

CREATE TABLE permission (
    id                  BIGSERIAL PRIMARY KEY,
    code                VARCHAR(80) NOT NULL,
    name                VARCHAR(120) NOT NULL,
    description         TEXT,
    module              VARCHAR(80) NOT NULL,
    status              permission_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_permission_code UNIQUE (code)
);

CREATE INDEX idx_permission_module_status ON permission (module, status);

CREATE TABLE app_role (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    name                VARCHAR(80) NOT NULL,
    description         TEXT,
    status              role_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_app_role_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_app_role_branch_name UNIQUE (branch_id, name),
    CONSTRAINT uq_app_role_id_branch UNIQUE (id, branch_id)
);

CREATE INDEX idx_app_role_branch_status ON app_role (branch_id, status);

CREATE TABLE role_permission (
    role_id             BIGINT NOT NULL,
    permission_id       BIGINT NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    PRIMARY KEY (role_id, permission_id),

    CONSTRAINT fk_role_permission_role
        FOREIGN KEY (role_id) REFERENCES app_role (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_role_permission_permission
        FOREIGN KEY (permission_id) REFERENCES permission (id)
        ON DELETE RESTRICT
);

CREATE INDEX idx_role_permission_permission ON role_permission (permission_id);

CREATE TABLE app_user (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    role_id             BIGINT NOT NULL,
    first_name          VARCHAR(100) NOT NULL,
    last_name           VARCHAR(100) NOT NULL,
    middle_name         VARCHAR(100),
    username            VARCHAR(50) NOT NULL,
    email               VARCHAR(150),
    password_hash       VARCHAR(60) NOT NULL,
    phone               VARCHAR(30),
    status              user_status NOT NULL DEFAULT 'ACTIVE',
    last_login_at       TIMESTAMPTZ,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_app_user_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_app_user_role_branch
        FOREIGN KEY (role_id, branch_id) REFERENCES app_role (id, branch_id)
        ON DELETE RESTRICT
);

CREATE UNIQUE INDEX uq_app_user_branch_username_ci
    ON app_user (branch_id, LOWER(username));

CREATE UNIQUE INDEX uq_app_user_branch_email_ci
    ON app_user (branch_id, LOWER(email))
    WHERE email IS NOT NULL;

CREATE INDEX idx_app_user_branch_role ON app_user (branch_id, role_id);
CREATE INDEX idx_app_user_branch_status ON app_user (branch_id, status);
CREATE INDEX idx_app_user_last_login_at ON app_user (last_login_at);

-- =========================================================
-- CATALOGS
-- =========================================================

CREATE TABLE product_category (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    name                VARCHAR(120) NOT NULL,
    description         TEXT,
    status              category_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_product_category_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_product_category_branch_name UNIQUE (branch_id, name)
);

CREATE INDEX idx_product_category_branch_status
    ON product_category (branch_id, status);

CREATE TABLE product (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    category_id         BIGINT,
    sku                 VARCHAR(64) NOT NULL,
    barcode             VARCHAR(64),
    name                VARCHAR(180) NOT NULL,
    description         TEXT,
    unit_of_measure     VARCHAR(30) NOT NULL DEFAULT 'UNIT',
    cost_price          NUMERIC(14,2) NOT NULL DEFAULT 0,
    sale_price          NUMERIC(14,2) NOT NULL DEFAULT 0,
    min_stock           NUMERIC(14,3) NOT NULL DEFAULT 0,
    max_stock           NUMERIC(14,3),
    tracks_inventory    BOOLEAN NOT NULL DEFAULT TRUE,
    status              product_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_product_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES product_category (id)
        ON DELETE SET NULL,

    CONSTRAINT chk_product_cost_price_non_negative
        CHECK (cost_price >= 0),

    CONSTRAINT chk_product_sale_price_non_negative
        CHECK (sale_price >= 0),

    CONSTRAINT chk_product_min_stock_non_negative
        CHECK (min_stock >= 0),

    CONSTRAINT chk_product_max_stock_valid
        CHECK (max_stock IS NULL OR max_stock >= min_stock)
);

CREATE UNIQUE INDEX uq_product_branch_sku
    ON product (branch_id, sku);

CREATE UNIQUE INDEX uq_product_branch_barcode
    ON product (branch_id, barcode)
    WHERE barcode IS NOT NULL;

CREATE INDEX idx_product_branch_category
    ON product (branch_id, category_id);

CREATE INDEX idx_product_branch_status
    ON product (branch_id, status);

CREATE INDEX idx_product_branch_name
    ON product (branch_id, name);

-- =========================================================
-- WAREHOUSES / INVENTORY
-- =========================================================

CREATE TABLE warehouse (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    name                VARCHAR(120) NOT NULL,
    type                warehouse_type NOT NULL,
    phone               VARCHAR(30),
    is_active           BOOLEAN NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_warehouse_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_warehouse_branch_name UNIQUE (branch_id, name)
);

CREATE INDEX idx_warehouse_branch_active
    ON warehouse (branch_id, is_active);

CREATE INDEX idx_warehouse_branch_type
    ON warehouse (branch_id, type);

CREATE TABLE inventory (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    warehouse_id        BIGINT NOT NULL,
    product_id          BIGINT NOT NULL,
    stock_on_hand       NUMERIC(14,3) NOT NULL DEFAULT 0,
    stock_reserved      NUMERIC(14,3) NOT NULL DEFAULT 0,
    stock_available     NUMERIC(14,3) GENERATED ALWAYS AS (stock_on_hand - stock_reserved) STORED,
    average_cost        NUMERIC(14,2) NOT NULL DEFAULT 0,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_inventory_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_inventory_warehouse
        FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id) REFERENCES product (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_inventory_warehouse_product UNIQUE (warehouse_id, product_id),

    CONSTRAINT chk_inventory_stock_on_hand_non_negative
        CHECK (stock_on_hand >= 0),

    CONSTRAINT chk_inventory_stock_reserved_non_negative
        CHECK (stock_reserved >= 0),

    CONSTRAINT chk_inventory_reserved_not_greater_than_on_hand
        CHECK (stock_reserved <= stock_on_hand),

    CONSTRAINT chk_inventory_average_cost_non_negative
        CHECK (average_cost >= 0)
);

CREATE INDEX idx_inventory_branch_warehouse
    ON inventory (branch_id, warehouse_id);

CREATE INDEX idx_inventory_branch_product
    ON inventory (branch_id, product_id);

CREATE INDEX idx_inventory_low_stock
    ON inventory (branch_id, stock_available);

CREATE TABLE inventory_movement (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    warehouse_id        BIGINT NOT NULL,
    product_id          BIGINT NOT NULL,
    user_id             BIGINT NOT NULL,
    movement_type       inventory_movement_type NOT NULL,
    quantity            NUMERIC(14,3) NOT NULL,
    stock_before        NUMERIC(14,3) NOT NULL,
    stock_after         NUMERIC(14,3) NOT NULL,
    unit_cost           NUMERIC(14,2),
    reference_type      VARCHAR(50),
    reference_id        BIGINT,
    notes               TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_inventory_movement_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_inventory_movement_warehouse
        FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_inventory_movement_product
        FOREIGN KEY (product_id) REFERENCES product (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_inventory_movement_user
        FOREIGN KEY (user_id) REFERENCES app_user (id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_inventory_movement_quantity_positive
        CHECK (quantity > 0),

    CONSTRAINT chk_inventory_movement_stock_before_non_negative
        CHECK (stock_before >= 0),

    CONSTRAINT chk_inventory_movement_stock_after_non_negative
        CHECK (stock_after >= 0),

    CONSTRAINT chk_inventory_movement_unit_cost_non_negative
        CHECK (unit_cost IS NULL OR unit_cost >= 0)
);

CREATE INDEX idx_inventory_movement_branch_created_at
    ON inventory_movement (branch_id, created_at DESC);

CREATE INDEX idx_inventory_movement_branch_product_created_at
    ON inventory_movement (branch_id, product_id, created_at DESC);

CREATE INDEX idx_inventory_movement_branch_warehouse_created_at
    ON inventory_movement (branch_id, warehouse_id, created_at DESC);

CREATE INDEX idx_inventory_movement_reference
    ON inventory_movement (reference_type, reference_id)
    WHERE reference_type IS NOT NULL AND reference_id IS NOT NULL;

-- =========================================================
-- CUSTOMERS
-- =========================================================

CREATE TABLE customer (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    name                VARCHAR(180) NOT NULL,
    customer_type       customer_type NOT NULL DEFAULT 'GENERAL',
    tax_id              VARCHAR(30),
    phone               VARCHAR(30),
    email               VARCHAR(150),
    address_line_1      VARCHAR(200),
    address_line_2      VARCHAR(200),
    city                VARCHAR(100),
    state               VARCHAR(100),
    country             VARCHAR(100),
    postal_code         VARCHAR(20),
    is_active           BOOLEAN NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_customer_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE
);

CREATE INDEX idx_customer_branch_name
    ON customer (branch_id, name);

CREATE INDEX idx_customer_branch_active
    ON customer (branch_id, is_active);

CREATE INDEX idx_customer_branch_email
    ON customer (branch_id, email)
    WHERE email IS NOT NULL;

CREATE INDEX idx_customer_branch_tax_id
    ON customer (branch_id, tax_id)
    WHERE tax_id IS NOT NULL;

-- =========================================================
-- SALES / POS
-- =========================================================

CREATE TABLE sale (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    warehouse_id        BIGINT NOT NULL,
    user_id             BIGINT NOT NULL,
    customer_id         BIGINT,
    sale_folio          VARCHAR(50) NOT NULL,
    sale_date           TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    subtotal            NUMERIC(14,2) NOT NULL DEFAULT 0,
    discount_total      NUMERIC(14,2) NOT NULL DEFAULT 0,
    tax_total           NUMERIC(14,2) NOT NULL DEFAULT 0,
    total               NUMERIC(14,2) NOT NULL DEFAULT 0,
    status              sale_status NOT NULL DEFAULT 'PAID',
    sale_type           sale_type NOT NULL DEFAULT 'COUNTER',
    notes               TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_sale_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_sale_warehouse
        FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_sale_user
        FOREIGN KEY (user_id) REFERENCES app_user (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_sale_customer
        FOREIGN KEY (customer_id) REFERENCES customer (id)
        ON DELETE SET NULL,

    CONSTRAINT uq_sale_branch_folio UNIQUE (branch_id, sale_folio),

    CONSTRAINT chk_sale_subtotal_non_negative
        CHECK (subtotal >= 0),

    CONSTRAINT chk_sale_discount_total_non_negative
        CHECK (discount_total >= 0),

    CONSTRAINT chk_sale_tax_total_non_negative
        CHECK (tax_total >= 0),

    CONSTRAINT chk_sale_total_non_negative
        CHECK (total >= 0)
);

CREATE INDEX idx_sale_branch_sale_date
    ON sale (branch_id, sale_date DESC);

CREATE INDEX idx_sale_branch_status_sale_date
    ON sale (branch_id, status, sale_date DESC);

CREATE INDEX idx_sale_branch_user_sale_date
    ON sale (branch_id, user_id, sale_date DESC);

CREATE INDEX idx_sale_branch_customer_sale_date
    ON sale (branch_id, customer_id, sale_date DESC)
    WHERE customer_id IS NOT NULL;

CREATE INDEX idx_sale_branch_warehouse_sale_date
    ON sale (branch_id, warehouse_id, sale_date DESC);

CREATE TABLE sale_item (
    id                  BIGSERIAL PRIMARY KEY,
    sale_id             BIGINT NOT NULL,
    product_id          BIGINT NOT NULL,
    quantity            NUMERIC(14,3) NOT NULL,
    unit_price          NUMERIC(14,2) NOT NULL,
    unit_discount       NUMERIC(14,2) NOT NULL DEFAULT 0,
    unit_tax            NUMERIC(14,2) NOT NULL DEFAULT 0,
    line_subtotal       NUMERIC(14,2) NOT NULL,
    line_total          NUMERIC(14,2) NOT NULL,

    CONSTRAINT fk_sale_item_sale
        FOREIGN KEY (sale_id) REFERENCES sale (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_sale_item_product
        FOREIGN KEY (product_id) REFERENCES product (id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_sale_item_quantity_positive
        CHECK (quantity > 0),

    CONSTRAINT chk_sale_item_unit_price_non_negative
        CHECK (unit_price >= 0),

    CONSTRAINT chk_sale_item_unit_discount_non_negative
        CHECK (unit_discount >= 0),

    CONSTRAINT chk_sale_item_unit_tax_non_negative
        CHECK (unit_tax >= 0),

    CONSTRAINT chk_sale_item_line_subtotal_non_negative
        CHECK (line_subtotal >= 0),

    CONSTRAINT chk_sale_item_line_total_non_negative
        CHECK (line_total >= 0)
);

CREATE INDEX idx_sale_item_sale
    ON sale_item (sale_id);

CREATE INDEX idx_sale_item_product
    ON sale_item (product_id);

CREATE INDEX idx_sale_item_sale_product
    ON sale_item (sale_id, product_id);

CREATE TABLE payment_method (
    id                  BIGSERIAL PRIMARY KEY,
    code                payment_method_code NOT NULL,
    name                VARCHAR(80) NOT NULL,
    description         TEXT,
    status              payment_method_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_payment_method_code UNIQUE (code)
);

CREATE INDEX idx_payment_method_status
    ON payment_method (status);

CREATE TABLE sale_payment (
    id                  BIGSERIAL PRIMARY KEY,
    sale_id             BIGINT NOT NULL,
    payment_method_id   BIGINT NOT NULL,
    amount              NUMERIC(14,2) NOT NULL,
    payment_reference   VARCHAR(100),
    paid_at             TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_sale_payment_sale
        FOREIGN KEY (sale_id) REFERENCES sale (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_sale_payment_method
        FOREIGN KEY (payment_method_id) REFERENCES payment_method (id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_sale_payment_amount_positive
        CHECK (amount > 0)
);

CREATE INDEX idx_sale_payment_sale
    ON sale_payment (sale_id);

CREATE INDEX idx_sale_payment_method
    ON sale_payment (payment_method_id);

CREATE INDEX idx_sale_payment_paid_at
    ON sale_payment (paid_at DESC);

-- =========================================================
-- SUPPLIERS / PURCHASES
-- =========================================================

CREATE TABLE supplier (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    name                VARCHAR(180) NOT NULL,
    tax_id              VARCHAR(30),
    phone               VARCHAR(30),
    email               VARCHAR(150),
    address_line_1      VARCHAR(200),
    address_line_2      VARCHAR(200),
    city                VARCHAR(100),
    state               VARCHAR(100),
    country             VARCHAR(100),
    postal_code         VARCHAR(20),
    status              supplier_status NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_supplier_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE
);

CREATE INDEX idx_supplier_branch_name
    ON supplier (branch_id, name);

CREATE INDEX idx_supplier_branch_status
    ON supplier (branch_id, status);

CREATE INDEX idx_supplier_branch_tax_id
    ON supplier (branch_id, tax_id)
    WHERE tax_id IS NOT NULL;

CREATE TABLE purchase (
    id                  BIGSERIAL PRIMARY KEY,
    branch_id           BIGINT NOT NULL,
    supplier_id         BIGINT NOT NULL,
    warehouse_id        BIGINT NOT NULL,
    user_id             BIGINT NOT NULL,
    purchase_folio      VARCHAR(50) NOT NULL,
    purchase_date       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    subtotal            NUMERIC(14,2) NOT NULL DEFAULT 0,
    tax_total           NUMERIC(14,2) NOT NULL DEFAULT 0,
    total               NUMERIC(14,2) NOT NULL DEFAULT 0,
    status              purchase_status NOT NULL DEFAULT 'RECEIVED',
    notes               TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_purchase_branch
        FOREIGN KEY (branch_id) REFERENCES branch (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_purchase_supplier
        FOREIGN KEY (supplier_id) REFERENCES supplier (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_purchase_warehouse
        FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_purchase_user
        FOREIGN KEY (user_id) REFERENCES app_user (id)
        ON DELETE RESTRICT,

    CONSTRAINT uq_purchase_branch_folio UNIQUE (branch_id, purchase_folio),

    CONSTRAINT chk_purchase_subtotal_non_negative
        CHECK (subtotal >= 0),

    CONSTRAINT chk_purchase_tax_total_non_negative
        CHECK (tax_total >= 0),

    CONSTRAINT chk_purchase_total_non_negative
        CHECK (total >= 0)
);

CREATE INDEX idx_purchase_branch_purchase_date
    ON purchase (branch_id, purchase_date DESC);

CREATE INDEX idx_purchase_branch_supplier_date
    ON purchase (branch_id, supplier_id, purchase_date DESC);

CREATE INDEX idx_purchase_branch_warehouse_date
    ON purchase (branch_id, warehouse_id, purchase_date DESC);

CREATE INDEX idx_purchase_branch_status_date
    ON purchase (branch_id, status, purchase_date DESC);

CREATE TABLE purchase_item (
    id                  BIGSERIAL PRIMARY KEY,
    purchase_id         BIGINT NOT NULL,
    product_id          BIGINT NOT NULL,
    quantity            NUMERIC(14,3) NOT NULL,
    unit_cost           NUMERIC(14,2) NOT NULL,
    line_subtotal       NUMERIC(14,2) NOT NULL,
    line_total          NUMERIC(14,2) NOT NULL,

    CONSTRAINT fk_purchase_item_purchase
        FOREIGN KEY (purchase_id) REFERENCES purchase (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_purchase_item_product
        FOREIGN KEY (product_id) REFERENCES product (id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_purchase_item_quantity_positive
        CHECK (quantity > 0),

    CONSTRAINT chk_purchase_item_unit_cost_non_negative
        CHECK (unit_cost >= 0),

    CONSTRAINT chk_purchase_item_line_subtotal_non_negative
        CHECK (line_subtotal >= 0),

    CONSTRAINT chk_purchase_item_line_total_non_negative
        CHECK (line_total >= 0)
);

CREATE INDEX idx_purchase_item_purchase
    ON purchase_item (purchase_id);

CREATE INDEX idx_purchase_item_product
    ON purchase_item (product_id);

CREATE INDEX idx_purchase_item_purchase_product
    ON purchase_item (purchase_id, product_id);

-- =========================================================
-- SEED SECTORS
-- =========================================================

INSERT INTO sector (name, description)
VALUES
    ('Agricultura, ganadería, silvicultura y pesca', 'Actividades del sector primario relacionadas con el campo, ganadería, bosques y pesca'),
    ('Minería', 'Extracción de minerales, petróleo, gas y otros recursos naturales'),
    ('Manufactura', 'Transformación industrial de materias primas en productos terminados o semiterminados'),
    ('Construcción', 'Obras civiles, edificación e infraestructura'),
    ('Comercio', 'Actividades de compra y venta al mayoreo, menudeo, físico o digital'),
    ('Transporte y logística', 'Transporte, almacenamiento, distribución y servicios logísticos'),
    ('Hotelería y turismo', 'Hospedaje, alimentos, turismo y servicios relacionados'),
    ('Tecnología', 'Desarrollo de software, hardware, telecomunicaciones y servicios digitales'),
    ('Servicios financieros', 'Banca, seguros, inversiones y demás actividades financieras'),
    ('Bienes raíces', 'Compra, venta, renta, administración y desarrollo de inmuebles'),
    ('Salud', 'Servicios médicos, hospitalarios, farmacéuticos y relacionados con la salud'),
    ('Educación', 'Escuelas, universidades, capacitación y servicios educativos'),
    ('Servicios profesionales', 'Consultoría, contabilidad, legal, ingeniería y servicios especializados'),
    ('Servicios industriales', 'Mantenimiento, reparación, soporte técnico y operación industrial'),
    ('Energía y servicios públicos', 'Electricidad, agua, gas, energías renovables y utilidades'),
    ('Gobierno y sector público', 'Instituciones gubernamentales y administración pública'),
    ('Organizaciones sin fines de lucro', 'Asociaciones civiles, fundaciones y organismos no lucrativos'),
    ('Entretenimiento y medios', 'Publicidad, medios, arte, cultura y entretenimiento'),
    ('Seguridad', 'Seguridad privada, vigilancia y protección'),
    ('Otro', 'Sector no contemplado en el catálogo base');

-- =========================================================
-- SEED COUNTRY
-- =========================================================

INSERT INTO country (code, name)
VALUES ('MX', 'México');

-- =========================================================
-- SEED STATES OF MEXICO
-- =========================================================

INSERT INTO state (country_id, code, name)
SELECT c.id, s.code, s.name
FROM country c
JOIN (
    VALUES
        ('AGS', 'Aguascalientes'),
        ('BCN', 'Baja California'),
        ('BCS', 'Baja California Sur'),
        ('CAM', 'Campeche'),
        ('CHP', 'Chiapas'),
        ('CHH', 'Chihuahua'),
        ('CMX', 'Ciudad de México'),
        ('COA', 'Coahuila'),
        ('COL', 'Colima'),
        ('DUR', 'Durango'),
        ('GUA', 'Guanajuato'),
        ('GRO', 'Guerrero'),
        ('HID', 'Hidalgo'),
        ('JAL', 'Jalisco'),
        ('MEX', 'Estado de México'),
        ('MIC', 'Michoacán'),
        ('MOR', 'Morelos'),
        ('NAY', 'Nayarit'),
        ('NLE', 'Nuevo León'),
        ('OAX', 'Oaxaca'),
        ('PUE', 'Puebla'),
        ('QUE', 'Querétaro'),
        ('ROO', 'Quintana Roo'),
        ('SLP', 'San Luis Potosí'),
        ('SIN', 'Sinaloa'),
        ('SON', 'Sonora'),
        ('TAB', 'Tabasco'),
        ('TAM', 'Tamaulipas'),
        ('TLA', 'Tlaxcala'),
        ('VER', 'Veracruz'),
        ('YUC', 'Yucatán'),
        ('ZAC', 'Zacatecas')
) AS s(code, name)
    ON 1 = 1
WHERE c.code = 'MX';

-- =========================================================
-- SEED BASIC PAYMENT METHODS
-- =========================================================

INSERT INTO payment_method (code, name, description)
VALUES
    ('CASH', 'Cash', 'Cash payment'),
    ('CARD', 'Card', 'Debit or credit card'),
    ('TRANSFER', 'Bank Transfer', 'Electronic bank transfer'),
    ('MOBILE_PAYMENT', 'Mobile Payment', 'Digital wallet or mobile payment'),
    ('MIXED', 'Mixed', 'Multiple payment methods'),
    ('OTHER', 'Other', 'Other payment method');

COMMIT;