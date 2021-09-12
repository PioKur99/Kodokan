import { NgModule } from '@angular/core';
import { RouterModule, RouterOutlet, Routes } from '@angular/router';
import { ReceptionistPanelComponent } from './receptionist-panel.component';
import { CustomersComponent } from './customers/customers.component';
import { EntranceComponent } from './entrance/entrance.component';
import { MembershipCardsComponent } from './membership-cards/membership-cards.component';
import { PassesComponent } from './passes/passes.component';
import { PassDetailsComponent } from './pass-details/pass-details.component';
import { AddAClientComponent } from './add-a-client/add-a-client.component';
import { EntryRegistrationComponent } from './entry-registration/entry-registration.component';
import { AddPackageReceptionistComponent } from './add-package-receptionist/add-package-receptionist.component';
import { CustomerDataComponent } from './customer-data/customer-data.component';
import { CustomerEditDataComponent } from './customer-edit-data/customer-edit-data.component';
import { CustomerFamilyComponent } from './customer-family/customer-family.component';
import { CustomerAddPassComponent } from './customer-add-pass/customer-add-pass.component';
import { CustomerShowEntrancesComponent } from './customer-show-entrances/customer-show-entrances.component';
import { CustomerSendMailComponent } from './customer-send-mail/customer-send-mail.component';
import { CustomerAddFamilyMemberComponent } from './customer-add-family-member/customer-add-family-member.component';


const routes: Routes = [
  {
    path: '', component: ReceptionistPanelComponent, children: [
      { path: 'customers', component: CustomersComponent, outlet: 'primary' },
      { path: 'entrance', component: EntranceComponent, outlet: 'primary' },
      { path: 'membership-cards', component: MembershipCardsComponent, outlet: 'primary' },
      { path: 'passes', component: PassesComponent, outlet: 'primary' },
      { path: 'passes/:packageId', component: PassDetailsComponent, outlet: 'primary' },
      { path: 'add-a-client', component: AddAClientComponent, outlet: 'primary' },
      { path: 'add-package-receptionist', component: AddPackageReceptionistComponent, outlet: 'primary' },
      { path: 'entry-registration', component: EntryRegistrationComponent, outlet: 'primary' },
      { path: 'customer-data', component: CustomerDataComponent, outlet: 'primary' },
      { path: 'customer-edit-data', component: CustomerEditDataComponent, outlet: 'primary' },
      { path: 'customer-family', component: CustomerFamilyComponent, outlet: 'primary' },
      { path: 'customer-add-pass', component: CustomerAddPassComponent, outlet: 'primary' },
      { path: 'customer-show-passes', component: CustomerAddPassComponent, outlet: 'primary' },
      { path: 'customer-show-entrances', component: CustomerShowEntrancesComponent, outlet: 'primary' },
      { path: 'customer-send-mail', component: CustomerSendMailComponent, outlet: 'primary' },
      { path: 'customer-add-family-member', component: CustomerAddFamilyMemberComponent, outlet: 'primary' }
      
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReceptionistPanelRoutingModule { }
