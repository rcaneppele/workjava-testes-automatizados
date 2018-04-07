<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:template title="Reajustes do funcionário">
	<jsp:body>
		<div class="container">
			<h1 class="text-center">Reajustes do funcionário</h1>
			
			<dl class="text-center row">
				<div class="col-sm">
					<dt>Nome</dt>
					<dd>${funcionario.nome}</dd>
				</div>
				
				<div class="col-sm">
					<dt>Admissão</dt>
					<fmt:parseDate value="${funcionario.dataDeAdmissao}" pattern="yyyy-MM-dd" var="parse" />
					<fmt:formatDate value="${parse}" pattern="dd/MM/yyyy" var="formatada" />
					<dd>${formatada}</dd>
				</div>
			
				<div class="col-sm">
					<dt>Cargo</dt>
					<dd>${funcionario.cargo.nome}</dd>
				</div>
			
				<div class="col-sm">
					<dt>Salário</dt>
					<dd>
						<fmt:formatNumber value="${funcionario.salario}" type="currency" />
					</dd>
				</div>
			</dl>

			<form method="post" action="<c:url value='/funcionarios/${funcionario.id}/reajustes' />">
				<div class="form-group">
					<label for="motivo">Motivo</label>
					<input id="motivo" name="motivo" class="form-control" required="required">
				</div>
				
				<div class="form-group">
					<label for="valor">Valor</label>
					<input id="valor" name="valor" class="form-control" required="required">
				</div>
				
				<input type="submit" value="Gravar" class="btn btn-primary">
			</form>

			<div class="table-responsive mt-2">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>DATA</th>
							<th>VALOR</th>
							<th>MOTIVO</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${reajustes}" var="reajuste">
							<tr>
								<td>
									<c:out value="${reajuste.id}"/>
								</td>
								
								<td>
									<fmt:parseDate value="${reajuste.data}" pattern="yyyy-MM-dd" var="parse" />
									<fmt:formatDate value="${parse}" pattern="dd/MM/yyyy" var="formatada" />
									<c:out value="${formatada}"/>
								</td>
								
								<td>
									<c:out value="${reajuste.valor}"/>
								</td>
								
								<td>
									<c:out value="${reajuste.motivo}"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</jsp:body>
</custom:template>
