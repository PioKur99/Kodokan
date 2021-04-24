import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewComponent } from './new/new.component';
import { RegisterComponent } from './register.component';
import { RegisteredComponent } from './registered/registered.component';

const routes: Routes = [{ path: '', component: RegisterComponent, children: [
  { path: '', redirectTo: 'new', pathMatch: 'full' }, 
  { path: 'new', component: NewComponent, data: { text: 'new' } },
  { path: 'registered', component: RegisteredComponent, data: { text: 'registered' } }] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
