import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerPanelComponent } from './customer-panel.component';
import { MyDataComponent } from './my-data/my-data.component';
import { MyFamilyComponent } from './my-family/my-family.component';
import { MyMembershipsComponent } from './my-memberships/my-memberships.component';
import { MyTrainingsComponent } from './my-trainings/my-trainings.component';
import { NewMembershipsComponent } from './new-memberships/new-memberships.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MainPageComponent } from './main-page/main-page.component';

const routes: Routes = [{ path: '', component: CustomerPanelComponent, children: [
{path: '', redirectTo: 'main-page', pathMatch: 'full' },
{path: 'main-page', component: MainPageComponent, data: {text: 'main-page'} },
{path: 'my-data', component: MyDataComponent, data: {text: 'my-data'} },
{path: 'my-trainings', component: MyTrainingsComponent, data: {text: 'my-trainings'} },
{path: 'my-family', component: MyFamilyComponent, data: {text: 'my-family'} },
{path: 'my-memberships', component: MyMembershipsComponent, data: {text: 'my-memberships'} },
{path: 'new-membership', component: NewMembershipsComponent, data: {text: 'new-membership'} }] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CustomerPanelRoutingModule { }
