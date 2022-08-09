package videoaula.br.com.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import videoaula.br.com.model.Todo
import videoaula.br.com.repository.TodoRepository

@Controller("/todos")
class TodoController(private val todoRepository: TodoRepository) {
    @Get
    fun getTodos(): List<Todo> {
        return todoRepository.findAll()
    }

    @Post
    fun addTodo(todo: Todo): HttpResponse<Todo> {
        return HttpResponse.created(todoRepository.save(todo))
    }
}