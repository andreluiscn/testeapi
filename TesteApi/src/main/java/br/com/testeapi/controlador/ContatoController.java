package br.com.testeapi.controlador;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeapi.modelo.Contato;
import br.com.testeapi.repositorio.Contatos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contatos")
@Api(value="/contatos",produces ="application/json")
public class ContatoController {
	
	@Autowired
	private Contatos contatos;	

	@GetMapping
	@ApiOperation(value="Lista os contatos",response=Collection.class)
	public List<Contato> listar() {
		return contatos.findAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Busca contato por id",response=Contato.class)
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		ResponseEntity<Contato> response = null;
		
		Optional<Contato> contato = contatos.findById(id);
		
		if (!contato.isPresent()) 
			response = ResponseEntity.notFound().build();
		else  
			response = ResponseEntity.ok(contato.get());
		
		return response;
	}
	
	@PostMapping
	@ApiOperation(value="Adiciona contato",response=Contato.class)	
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		return contatos.save(contato);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza contato",response=Contato.class)
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Contato contato) {
		ResponseEntity<Contato> response = null;
		
		Optional<Contato> existente = contatos.findById(id);
		
		if (!existente.isPresent()) {
			response =  ResponseEntity.notFound().build();
		} else { 
			Contato contatoExistente = existente != null ?  existente.get() : null;
			BeanUtils.copyProperties(contato, contatoExistente, "id");
			contatoExistente = contatos.save(contatoExistente);
			response = ResponseEntity.ok(contatoExistente);
		}
		
		return response;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Apaga contato",response=Contato.class)
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		ResponseEntity<Void> response = null;
		
		Optional<Contato> existente = contatos.findById(id);
		if (!existente.isPresent())
			response = ResponseEntity.notFound().build();
		else {
			contatos.delete(existente.get());
			response = ResponseEntity.noContent().build();
		}
		
		return response;
	}
}