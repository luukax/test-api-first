package br.com.southrocklab.resources;

import br.com.southrocklab.ApplicationTests;
import io.restassured.RestAssured;

import org.junit.Test;

public class CardResourceTest extends ApplicationTests {

    @Test
    public void deve_salvar_novo_card_no_sistema() {
    	RestAssured.given()
  	   .log().all()
  	   .contentType("application/json")
  	   .body("{\n"
  	   		+ "  \"brand\": \"VISA\",\n"
  	   		+ "  \"cvc\": \"111\",\n"
  	   		+ "  \"expirationMoth\": 1,\n"
  	   		+ "  \"expirationYear\": 2025,\n"
  	   		+ "  \"holderName\": \"LOREM IPSUM SILVA\",\n"
  	   		+ "  \"number\": \"1211111111111112\",\n"
  	   		+ "  \"customerId\": 1\n"
  	   		+ "}")
  	.when()
         .post("http://localhost:9090/card")  	
  	.then()
  	   .statusCode(200)
  	;
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_card_com_cvv_maior_que_999() {
    	RestAssured.given()
   	   .log().all()
   	   .contentType("application/json")
   	   .body("{\n"
   	   		+ "  \"brand\": \"VISA\",\n"
   	   		+ "  \"cvc\": \"1000\",\n"
   	   		+ "  \"expirationMoth\": 1,\n"
   	   		+ "  \"expirationYear\": 2025,\n"
   	   		+ "  \"holderName\": \"LOREM IPSUM SILVA\",\n"
   	   		+ "  \"number\": \"1211111111111112\",\n"
   	   		+ "  \"customerId\": 1\n"
   	   		+ "}")
   	.when()
          .post("http://localhost:9090/card")  	
   	.then()
   	   .statusCode(400);
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_card_com_expiration_month_maior_que_12() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	   .body("{\n"
    	   		+ "  \"brand\": \"VISA\",\n"
    	   		+ "  \"cvc\": \"100\",\n"
    	   		+ "  \"expirationMoth\": 13,\n"
    	   		+ "  \"expirationYear\": 2025,\n"
    	   		+ "  \"holderName\": \"LOREM IPSUM SILVA\",\n"
    	   		+ "  \"number\": \"1211111111111112\",\n"
    	   		+ "  \"customerId\": 1\n"
    	   		+ "}")
    	.when()
           .post("http://localhost:9090/card")  	
    	.then()
    	   .statusCode(400);
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_card_com_expiration_year_menor_que_2022() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	   .body("{\n"
    	   		+ "  \"brand\": \"VISA\",\n"
    	   		+ "  \"cvc\": \"100\",\n"
    	   		+ "  \"expirationMoth\": 1,\n"
    	   		+ "  \"expirationYear\": 2021,\n"
    	   		+ "  \"holderName\": \"LOREM IPSUM SILVA\",\n"
    	   		+ "  \"number\": \"1211111111111112\",\n"
    	   		+ "  \"customerId\": 1\n"
    	   		+ "}")
    	.when()
           .post("http://localhost:9090/card")  	
    	.then()
    	   .statusCode(400);
    }

    @Test
    public void deve_retornar_status_400_quando_salvar_card_com_number_de_15_digitos() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	   .body("{\n"
    	   		+ "  \"brand\": \"VISA\",\n"
    	   		+ "  \"cvc\": \"1000\",\n"
    	   		+ "  \"expirationMoth\": 1,\n"
    	   		+ "  \"expirationYear\": 2025,\n"
    	   		+ "  \"holderName\": \"LOREM IPSUM SILVA\",\n"
    	   		+ "  \"number\": \"1234123412345\",\n"
    	   		+ "  \"customerId\": 1\n"
    	   		+ "}")
    	.when()
           .post("http://localhost:9090/card")  	
    	.then()
    	   .statusCode(400);
    }

    @Test
    public void deve_deletar_um_card_salvo_no_sistema() {
    	RestAssured.given()
   	   .log().all()
   	   .contentType("application/json")
   	.when()
          .delete("http://localhost:9090/card/10")  	
   	.then()
   	   .statusCode(200);
    }

    @Test
    public void deve_retornar_status_404_ao_deletar_um_card_com_id_nao_salvo_no_sistema() {
    	RestAssured.given()
    	   .log().all()
    	   .contentType("application/json")
    	.when()
           .delete("http://localhost:9090/card/122")  	
    	.then()
    	   .statusCode(404);
    }
}
