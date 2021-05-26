import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MemberCardComponent } from './member-card.component';

const routes: Routes = [{ path: '', component: MemberCardComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberCardRoutingModule { }
