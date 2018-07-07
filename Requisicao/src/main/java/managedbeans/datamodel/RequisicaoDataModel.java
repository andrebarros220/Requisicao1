package managedbeans.datamodel;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import modelo.Requisicao;
import servicos.ServicoRequisicao;

public class RequisicaoDataModel extends ListDataModel<Requisicao> implements SelectableDataModel<Requisicao> {

	private static ServicoRequisicao servico = new ServicoRequisicao();

	public RequisicaoDataModel() {

	}

	public RequisicaoDataModel(List<Requisicao> list) {
		super(list);
	}

	@Override
	public Requisicao getRowData(String rowKey) {

		for (Requisicao f : servico.getRequisicoes())
			if (Integer.parseInt(rowKey) == f.getCodigo())
				return f;

		return null;
	}

	@Override
	public Object getRowKey(Requisicao arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
