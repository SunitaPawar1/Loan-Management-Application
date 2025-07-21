
package com.loanmanagement.loansystem.Repository;

import com.loanmanagement.loansystem.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
