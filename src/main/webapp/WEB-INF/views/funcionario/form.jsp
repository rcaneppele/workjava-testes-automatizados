<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:template title="Cadastrar novo Funcionário">
	<jsp:body>
		<h1 class="text-center">Cadastrar novo Funcionário</h1>
		
		<form method="post" action="<c:url value='/funcionarios' />">
			<div class="form-group">
				<label for="nome">Nome</label>
				<input id="nome" name="nome" class="form-control" required="required" value="${funcionario.nome}">
			</div>
			
			<div class="form-group">
				<label for="cargo">Cargo</label>
				<select id="cargo" name="cargo.id" class="form-control" required="required">
					<c:forEach items="${cargos}" var="cargo">
						<option value="${cargo.id}">${cargo.nome}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-group">
				<label for="salario">Salário</label>
				<input id="salario" name="salario" class="form-control" required="required" value="${funcionario.salario}">
			</div>
			
			<div class="form-group">
				<label for="dataDeAdmissao">Data de Admissão</label>
				<input id="dataDeAdmissao" name="dataDeAdmissao" class="form-control" required="required" value="${funcionario.dataDeAdmissao}">
			</div>
			
			<input type="submit" value="Gravar" class="btn btn-primary">
			<a href="<c:url value='/funcionarios' />" class="btn btn-light">Cancelar</a>
		</form>
	</jsp:body>
</custom:template>