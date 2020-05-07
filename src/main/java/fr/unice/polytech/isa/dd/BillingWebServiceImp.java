package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/billingService")
@Stateless(name = "BillingWS")
public class BillingWebServiceImp implements BillingWebService {


    @EJB
    private BillingGeneratedInterface bg;
    @EJB
    private CheckTransferStatus cs;

    @Override
    public boolean generateBill(){
        System.out.println("Générer les factures");
        bg.generateBill();
        return true;
    }

    @Override
    public List<Bill> getBills(){
        System.out.println("Lister les factures");
        return bg.get_bills();
    }


    @Override
    public String checkStatut(int id) {
        System.out.println("vérifier Statut Facture");
        if (cs.findBillById(id) != null) {
            if (cs.checkstatut(id)) {
                return "PAID";
            } else {
                return "UNPAID";
            }
        }
        return "NOT FOUND";
    }


    @Override
    public List<Bill> getAllPaidBills() throws ExternalPartnerException{
        System.out.println("Lister les factures payées");
        return cs.getAllPaidBills();
    }
}
