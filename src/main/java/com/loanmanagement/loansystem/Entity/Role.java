    package com.loanmanagement.loansystem.Entity;
    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "roles")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="role_name")
        private String roleName; // E.g., ADMIN, CUSTOMER

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
