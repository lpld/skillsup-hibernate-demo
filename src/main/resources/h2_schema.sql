CREATE TABLE Author (
  id      BIGINT       NOT NULL,
  country VARCHAR(255) NOT NULL,
  name    VARCHAR(255) NOT NULL,
  version INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE Book (
  id             BIGINT       NOT NULL,
  firstPublished DATE         NOT NULL,
  abstract       VARCHAR(255),
  titie          VARCHAR(255) NOT NULL,
  author_id      BIGINT       NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Book_Genre (
  Book_id   BIGINT NOT NULL,
  genres_id BIGINT NOT NULL
);

CREATE TABLE BookStore (
  id      BIGINT       NOT NULL,
  city    VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  street1 VARCHAR(255),
  street2 VARCHAR(255),
  zip     VARCHAR(255),
  name    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Genre (
  id   BIGINT       NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Magazine (
  id      BIGINT       NOT NULL,
  summary VARCHAR(255),
  title   VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Publication (
  type         VARCHAR(31)  NOT NULL,
  id           BIGINT       NOT NULL,
  date         DATE         NOT NULL,
  isbn         VARCHAR(255),
  name         VARCHAR(255) NOT NULL,
  summary      VARCHAR(255),
  publisher_id BIGINT       NOT NULL,
  book_id      BIGINT       NOT NULL,
  magazine_id  BIGINT       NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Publication_BookStore (
  publications_id BIGINT NOT NULL,
  bookStores_id   BIGINT NOT NULL
);

CREATE TABLE Publisher (
  id   BIGINT       NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE Book ADD CONSTRAINT FK5gbo4o7yxefxivwuqjichc67t FOREIGN KEY (author_id) REFERENCES Author;
ALTER TABLE Book_Genre ADD CONSTRAINT FKt34eempqednktdwgrwnw7o35u FOREIGN KEY (genres_id) REFERENCES Genre;
ALTER TABLE Book_Genre ADD CONSTRAINT FK63hovcio3flvfrf6db8utj32y FOREIGN KEY (Book_id) REFERENCES Book;
ALTER TABLE Publication ADD CONSTRAINT FK73xtxlre34ytcfa94qe08fqy2 FOREIGN KEY (publisher_id) REFERENCES Publisher;
ALTER TABLE Publication ADD CONSTRAINT FK11p3uphjf9nf0ribsoj1krbqq FOREIGN KEY (book_id) REFERENCES Book;
ALTER TABLE Publication ADD CONSTRAINT FKffs9p6pdh6afxynka8lkt9hmi FOREIGN KEY (magazine_id) REFERENCES Magazine;
ALTER TABLE Publication_BookStore ADD CONSTRAINT FKirwvyyt41o7hl0ye9lwighhpr FOREIGN KEY (bookStores_id) REFERENCES BookStore;
ALTER TABLE Publication_BookStore ADD CONSTRAINT FKkstv52mfcf676621hvib3onm5 FOREIGN KEY (publications_id) REFERENCES Publication;