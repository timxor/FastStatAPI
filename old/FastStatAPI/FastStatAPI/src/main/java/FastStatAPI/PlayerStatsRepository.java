// PlayerStatsRepository.java

package src.main.java.FastStatAPI;

import org.springframework.data.jpa.repository.JpaRepository;

interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

}
