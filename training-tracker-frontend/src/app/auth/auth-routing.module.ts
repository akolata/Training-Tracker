import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignInComponent} from "./component/sign-in/sign-in.component";
import {SignUpComponent} from "./component/sign-up/sign-up.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/auth/sign-in'},
  {path: 'sign-in', component: SignInComponent},
  {path: 'sign-up', component: SignUpComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule {
}
