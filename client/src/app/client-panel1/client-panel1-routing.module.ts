import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientPanel1Component } from './client-panel1.component';
import { Packages1Component } from './packages1/packages1.component';
import { PersonalData1Component } from './personal-data1/personal-data1.component';

const routes: Routes = [{ path: '', component: ClientPanel1Component, children: [{ path: '', redirectTo: 'packages1', pathMatch: 'full' }, { path: 'packages1', component: Packages1Component, data: { text: 'packages 1' } }, { path: 'personal-data1', component: PersonalData1Component, data: { text: 'personal-data 1' } }] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientPanel1RoutingModule {
}
