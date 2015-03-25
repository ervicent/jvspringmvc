package ufrr.editora.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDeVidaListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void beforePhase(PhaseEvent event) {
		System.out.println("Antes da fase: " + event.getPhaseId());
	}
	
	public void afterPhase(PhaseEvent event) {
		System.out.println("Depois da Fase: " + event.getPhaseId());
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
