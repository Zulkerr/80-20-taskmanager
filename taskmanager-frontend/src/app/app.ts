import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { TaskService, Task } from './services/task.spec';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class APPComponent implements OnInit {

  tasks: Task[] = [];
  newTaskTitle: string = "";

  constructor(
    private taskService: TaskService,
    private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.taskService.getTasks().subscribe(data => {
      this.tasks = data;
    });
  }

  addTask() {
    if (this.newTaskTitle.trim() == '') return;

    this.taskService.createTask(this.newTaskTitle).subscribe({
      next: (task) => {
        this.tasks.push(task);
        this.newTaskTitle = '';

        this.cdr.detectChanges();
      },
      error: (err) =>console.error('Fehler beim Speichern:', err)
    });
  }

  completeTask(task: Task) {
    if(task.id) {
      this.taskService.completeTask(task.id).subscribe({
        next: (updatedTask) => {
          task.completed = updatedTask.completed;
          
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Fehler beim Update:', err)
      
      });
    }
  }
}
