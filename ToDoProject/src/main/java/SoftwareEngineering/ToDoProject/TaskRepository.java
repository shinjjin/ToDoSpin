package SoftwareEngineering.ToDoProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Das Interface TaskRepository erbt von JpaRepository, um CRUD-Operationen für die Task-Entität zu ermöglichen
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
