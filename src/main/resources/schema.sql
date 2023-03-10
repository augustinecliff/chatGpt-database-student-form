CREATE TABLE IF NOT EXISTS students (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        first_name VARCHAR(40) NOT NULL,
                                        last_name VARCHAR(40) NOT NULL,
                                        email VARCHAR(40) NOT NULL,
                                        phone_number VARCHAR(40) NOT NULL,
                                        date_of_birth DATE,
                                        gender VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS unit (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    name VARCHAR(40),
                                    code VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS student_unit (
                                            student_id INT NOT NULL,
                                            unit_id BIGINT NOT NULL,
                                            PRIMARY KEY (student_id, unit_id),
                                            CONSTRAINT fk_student_unit_student_id FOREIGN KEY (student_id) REFERENCES students(id),
                                            CONSTRAINT fk_student_unit_unit_id FOREIGN KEY (unit_id) REFERENCES unit(id)
);
