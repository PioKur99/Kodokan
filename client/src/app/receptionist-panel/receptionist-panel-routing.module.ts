import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceptionistPanelComponent } from './receptionist-panel.component';
import { CustomersComponent } from './customers/customers.component';
const routes: Routes = [{
  path: '', component: ReceptionistPanelComponent, children: []
},
{
  path: 'customers', component: CustomersComponent, outlet: "primary"
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReceptionistPanelRoutingModule { }
