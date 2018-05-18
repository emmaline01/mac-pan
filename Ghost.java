if (canMove(bDirSwitch.get( 0 )))
                {
                    direction = bDirSwitch.get(0);
                    if (direction.equals( "right" ))
                    {
                        x += 5;
                    }
                    else
                    {
                        x -= 5;
                    }
                }
                else
                {
                    bDirSwitch.add( bDirSwitch.remove( 0 ) );
                    direction = bDirSwitch.get(0);
                    if (direction.equals( "right" ))
                    {
                        x += 5;
                    }
                    else
                    {
                        x -= 5;
                    }
                }
