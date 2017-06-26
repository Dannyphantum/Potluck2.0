package enhanced.potluch;

import org.springframework.data.repository.CrudRepository;

import enhanced.potluch.Chefs;

public interface UserRepository extends CrudRepository<Chefs, Long> {

}
