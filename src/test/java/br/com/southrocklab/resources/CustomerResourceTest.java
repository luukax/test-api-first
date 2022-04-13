package br.com.southrocklab.resources;

import br.com.southrocklab.ApplicationTests;
import io.restassured.RestAssured;

import org.junit.Test;

public class CustomerResourceTest extends ApplicationTests {

    @Test
    public void deve_salvar_novo_customer_no_sistema() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	   .body("{\n"
    	   		+ "  \"birthDate\": \"1992-08-15\",\n"
    	   		+ "  \"lastName\": \"maeote\",\n"
    	   		+ "  \"name\": \"rbussta\"\n"
    	   		+ "}")
    	.when()
           .post("http://localhost:9090/customer")  	
    	.then()
    	   .statusCode(200)
    	;
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_customer_ja_salvo() {
    	RestAssured.given()
 	   .log().all()
 	   .contentType("application/json")
 	   .body("{\n"
 	   		+ "  \"birthDate\": \"1992-08-15\",\n"
 	   		+ "  \"lastName\": \"Lortes\",\n"
 	   		+ "  \"name\": \"restassured\"\n"
 	   		+ "}")
 	.when()
        .post("http://localhost:9090/customer")  	
 	.then()
 	   .statusCode(400)
 	;
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_customer_com_birth_date_maior_que_hoje() {
    	RestAssured.given()
  	   .log().all()
  	   .contentType("application/json")
  	   .body("{\n"
  	   		+ "  \"birthDate\": \"2022-08-15\",\n"
  	   		+ "  \"lastName\": \"Lors\",\n"
  	   		+ "  \"name\": \"roselito\"\n"
  	   		+ "}")
  	.when()
         .post("http://localhost:9090/customer")  	
  	.then()
  	   .statusCode(400)
  	;
    }

    @Test
    public void deve_procurar_customer_pelo_name_e_last_name() {
    	RestAssured.given()
  	   .log().all()
  	   .contentType("application/json")
  	   .body("{\n"
  	   		+ "  \"birthDate\": \"1992-08-15\",\n"
  	   		+ "  \"lastName\": \"Lortes\",\n"
  	   		+ "  \"name\": \"restassured\"\n"
  	   		+ "}")
  	.when()
         .post("http://localhost:9090/customer")  	
  	.then()
  	   .statusCode(400)
  	;
    }

    @Test
    public void deve_retornar_status_404_quando_buscar_customer_por_name_e_last_name_inexistente() {
    	RestAssured.given()
   	   .log().all()
   	   .contentType("application/json")
   	.when()
          .get("http://localhost:9090/customer/user/inesistente")  	
   	.then()
   	   .statusCode(404)
   	;
     }
    

    @Test
    public void deve_atualizar_o_last_name_de_um_customer() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	   .body("{\n"
    	   		+ "  \"birthDate\": \"1995-02-18\",\n"
    	   		+ "  \"lastName\": \"altetradopelorest\",\n"
    	   		+ "  \"name\": \"muracio\"\n"
    	   		+ "}")
    	.when()
           .put("http://localhost:9090/customer/4")  	
    	.then()
    	   .statusCode(200)
    	;
    }

    @Test
    public void deve_retornar_status_404_ao_atualizar_um_customer_com_id_nao_salvo_no_sistema() {
    	RestAssured.given()
 	   .log().all()
 	   .contentType("application/json")
 	   .body("{\n"
 	   		+ "  \"birthDate\": \"1995-02-18\",\n"
 	   		+ "  \"lastName\": \"altetradopelorest\",\n"
 	   		+ "  \"name\": \"muracio\"\n"
 	   		+ "}")
 	.when()
        .put("http://localhost:9090/customer/120")  	
 	.then()
 	   .statusCode(404);
    }

    @Test
    public void deve_deletar_um_customer_salvo_no_sistema() {
    	RestAssured.given()
  	   .log().all()
  	   .contentType("application/json")
  	.when()
         .delete("http://localhost:9090/customer/12")  	
  	.then()
  	   .statusCode(204);
    }

    @Test
    public void deve_retornar_status_404_ao_deletar_um_customer_com_id_nao_salvo_no_sistema() {
    	RestAssured.given()
   	   .log().all()
   	   .contentType("application/json")
   	.when()
          .delete("http://localhost:9090/customer/120")  	
   	.then()
   	   .statusCode(404);
    }
}
