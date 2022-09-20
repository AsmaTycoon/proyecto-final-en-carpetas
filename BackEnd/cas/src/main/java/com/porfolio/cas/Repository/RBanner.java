
package com.porfolio.cas.Repository;

import com.porfolio.cas.Entity.Banner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RBanner extends JpaRepository<Banner, Integer> {
    
    public Optional<Banner> findByImagenB(String imagenP);
    
    public boolean existsByImagenB(String imagenP);
}
