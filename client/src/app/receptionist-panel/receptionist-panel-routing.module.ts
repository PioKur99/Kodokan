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


const routes: Routes = [
  {
    path: '', component: ReceptionistPanelComponent, children: [
      { path: 'customers', component: CustomersComponent, outlet: 'primary' },
      { path: 'entrance', component: EntranceComponent, outlet: 'primary' },
      { path: 'membership-cards', component: MembershipCardsComponent, outlet: 'primary' },
      { path: 'passes', component: PassesComponent, outlet: 'primary' },
      { path: 'passes/:packageId', component: PassDetailsComponent, outlet: 'primary' },
      { path: 'add-a-client', component: AddAClientComponent, outlet: 'primary' },
      { path: 'entry-registration', component: EntryRegistrationComponent, outlet: 'primary' },
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReceptionistPanelRoutingModule { }
