package me.jose.credit.application.system.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.time.LocalDateTime

@RestControllerAdvice
// Nessa anotação esta dizendo Spring nessa classe tem alguns métodos que defini tratamento para minha exeções
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException:: class )
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<exceptionDetails>{
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach{
            erro: ObjectError ->
            val fieldName: String = (erro as FieldError).field
            val messagemError: String? = erro.defaultMessage
            erros[fieldName] = messagemError

        }
        return ResponseEntity(
            exceptionDetails(
                title = "Bad request: Consult the documention",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.objectName,
                details = erros
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException:: class )
    fun handlerValidException(ex: DataAccessException): ResponseEntity<exceptionDetails>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(

                exceptionDetails(
                    title = "Conflit: Consult the documention",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.CONFLICT.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
        )

       /* return ResponseEntity(
            exceptionDetails(
                title = "Bad request: Consult the documention",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.objectName,
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )*/

       @ExceptionHandler(BusinessException::class)
       fun handlerValidException(ex: BusinessException): ResponseEntity<exceptionDetails> {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(
                   exceptionDetails(
                       title = "Bad Request! Consult the documentation",
                       timestamp = LocalDateTime.now(),
                       status = HttpStatus.BAD_REQUEST.value(),
                       exception = ex.javaClass.toString(),
                       details = mutableMapOf(ex.cause.toString() to ex.message)
                   )
               )
       }

        @ExceptionHandler(IllegalArgumentException::class)
        fun handlerValidException(ex: IllegalArgumentException): ResponseEntity<exceptionDetails> {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    exceptionDetails(
                        title = "Bad Request! Consult the documentation",
                        timestamp = LocalDateTime.now(),
                        status = HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)
                    )
                )
        }
    }
}