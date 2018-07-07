package managedbeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Requisicao;
import servicos.ServicoRequisicao;

@FacesConverter("converterRequisicao")
public class RequisicaoConverter implements Converter {

	ServicoRequisicao servico = new ServicoRequisicao();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
			for (Requisicao v : servico.getRequisicoes())
				if (v.getNome_produto().equals(value))
					return v;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object requisicao) {
		if (requisicao == null || requisicao.equals("")) {
			return null;
		} else {
			return ((Requisicao) requisicao).getNome_produto();
		}

	}

}
